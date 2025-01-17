package com.jutools.script.olexp.parser;

import com.jutools.script.engine.instructions.DIV;
import com.jutools.script.engine.instructions.Instruction;
import com.jutools.script.engine.instructions.MOD;
import com.jutools.script.engine.instructions.MUL;
import com.jutools.script.parser.AbstractParser;
import com.jutools.script.parser.EndStatusType;
import com.jutools.script.parser.TransferBuilder;
import com.jutools.script.parser.TransferEventHandler;
import com.jutools.script.parser.TreeNode;

/**
 * *,/,% 연산 파싱 수행
 * 
 * @author jmsohn
 */
public class TermParser extends AbstractParser<Instruction> {
	
	/** *,/,% 연산의 첫번째 파라미터의 tree node */
	private TreeNode<Instruction> p1;
	
	/** *,/,% 연산의 두번째 파라미터의 tree node */
	private TreeNode<Instruction> p2;
	
	/** *,/,% 연산 tree node */
	private TreeNode<Instruction> op;

	/**
	 * 생성자
	 */
	public TermParser() throws Exception {
		super();
	}

	/**
	 * 시작상태 반환
	 */
	@Override
	protected String getStartStatus() {
		return "START";
	}
	
	/**
	 * 초기화 수행
	 */
	@Override
	protected void init() throws Exception {

		// 속성 변수 초기화
		this.p1 = null;
		this.p2 = null;
		this.op = null;
		
		// 상태 전이 맵 설정
		this.putTransferMap("START", new TransferBuilder()
				.add(" \t\r\n", "START")
				.add("^ \t\r\n", "FACTOR_1", -1)
				.build());
		
		this.putTransferMap("FACTOR_1", new TransferBuilder()
				.add(" \t\r\n", "FACTOR_1")
				.add("\\*\\/\\%", "OPERATION")
				.add("^ \t\r\n\\*\\/", "END", -1)
				.build());
		
		this.putTransferMap("OPERATION", new TransferBuilder()
				.add(" \t\r\n", "OPERATION")
				.add("^ \t\r\n", "FACTOR_2", -1)
				.build());
		
		this.putTransferMap("FACTOR_2", new TransferBuilder()
				.add(" \t\r\n", "FACTOR_2")
				.add("\\*\\/\\%", "OPERATION")
				.add("^ \t\r\n\\*\\/", "END", -1)
				.build());
		
		// 종료 상태 추가
		this.putEndStatus("FACTOR_1");
		this.putEndStatus("FACTOR_2");
		this.putEndStatus("END", EndStatusType.IMMEDIATELY_END);
	}
	
	/**
	 * *,/,% 의 첫번째 파라미터 상태로 전이시 핸들러 메소드
	 * 
	 * @param event
	 */
	@TransferEventHandler(
			source={"START"},
			target={"FACTOR_1"}
	)
	public void handleP1(Event event) throws Exception {
		
		FactorParser parser = new FactorParser();
		this.p1 = parser.parse(event.getReader());
	}
	
	/**
	 * *,/,%의 연산자 상태로 전이시 핸들러 메소드
	 * 
	 * @param event
	 */
	@TransferEventHandler(
			source={"FACTOR_1", "FACTOR_2"},
			target={"OPERATION"}
	)
	public void handleOp(Event event) throws Exception {
		
		// 연산자 노드 생성
		if(event.getCh() == '*') {
			this.op = new TreeNode<>(new MUL());
		} else if(event.getCh() == '/') {
			this.op = new TreeNode<>(new DIV());
		} else if(event.getCh() == '%') {
			this.op = new TreeNode<>(new MOD());
		} else {
			throw new Exception("Unexpected operation:" + event.getCh());
		}
		
		// 연산자 노드에 p1 추가
		this.op.addChild(this.p1);
		
		// 
		this.p1 = this.op;
	}
	
	/**
	 * *,/,%의 첫번째 파라미터 상태로 전이시 핸들러 메소드
	 * 
	 * @param event
	 */
	@TransferEventHandler(
			source={"OPERATION"},
			target={"FACTOR_2"}
	)
	public void handleP2(Event event) throws Exception {
		
		FactorParser parser = new FactorParser();
		this.p2 = parser.parse(event.getReader());
		
		// 두번째 파라미터 추가
		this.op.addChild(this.p2);
	}
	
	/**
	 * 파싱 종료 처리
	 */
	@Override
	protected void exit() throws Exception {
		
		// 현재 노드 설정
		this.setNode(this.p1);
	}
}
