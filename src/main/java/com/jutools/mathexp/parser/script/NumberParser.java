package com.jutools.mathexp.parser.script;

import com.jutools.mathexp.instructions.Instruction;
import com.jutools.mathexp.instructions.LOAD;
import com.jutools.mathexp.parser.AbstractParser;
import com.jutools.mathexp.parser.TransferBuilder;
import com.jutools.mathexp.parser.TransferEventHandler;

/**
 * 
 * @author jmsohn
 */
public class NumberParser extends AbstractParser<Instruction> {
	
	/** */
	private StringBuffer buffer;

	/**
	 * 
	 */
	public NumberParser() throws Exception {
		super();
		this.buffer = new StringBuffer();
	}

	@Override
	protected String getStartStatus() {
		return "START";
	}

	/**
	 * 파싱전 초기화 수행
	 */
	@Override
	protected void init() throws Exception {
		
		// 상태 변환 맵 추가
		this.putTransferMap("START", new TransferBuilder()
				.add("0-9", "NUMBER")
				.add("\\-", "SIGN")
				.add("^0-9\\-", "ERROR")
				.build());
		
		this.putTransferMap("SIGN", new TransferBuilder()
				.add("0-9", "NUMBER")
				.add("^0-9", "ERROR", true)
				.build());
		
		this.putTransferMap("NUMBER", new TransferBuilder()
				.add("0-9", "NUMBER")
				.add("\\.", "DOT")
				.add("^0-9\\.", "END", true)
				.build());
		
		this.putTransferMap("DOT", new TransferBuilder()
				.add("0-9", "FLOATING_NUMBER")
				.add("^0-9", "END", true)
				.build());
		
		this.putTransferMap("FLOATING_NUMBER", new TransferBuilder()
				.add("0-9", "FLOATING_NUMBER")
				.add("^0-9", "END", true)
				.build());
		
		// 종료 상태 추가
		this.putEndStatus("NUMBER");
		this.putEndStatus("FLOATING_NUMBER");
		this.putEndStatus("END", 1); // END 상태로 들어오면 Parsing을 중지
	}
	
	@TransferEventHandler(
			source={"START", "SIGN", "NUMBER", "DOT", "FLOATING_NUMBER"},
			target={"SIGN", "NUMBER", "DOT", "FLOATING_NUMBER"}
	)
	public void handleNumber(Event event) {
		this.buffer.append(event.getChar());
	}
	
	/**
	 * 파싱 종료 처리
	 */
	protected void exit() throws Exception {
		
		// LOAD "숫자"
		LOAD inst = new LOAD();
		inst.addParam(this.buffer.toString());
		this.setNodeData(inst);
	}

}