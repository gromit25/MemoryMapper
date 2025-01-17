package com.jutools.publish.formatter.flow;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.jutools.publish.formatter.Formatter;
import com.jutools.publish.formatter.FormatterAttr;
import com.jutools.publish.formatter.FormatterException;
import com.jutools.publish.formatter.FormatterSpec;
import com.jutools.script.olexp.OLExp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * foreach formatter<br>
 * foreach formatter는 List를 반복수행 함, alt테그를 포함할 수 있으며, List가 비어있을때,<br>
 * alt테그 내부에 메시지를 출력함<br>
 * foreach formatter는 하나의 FlowFormatter를<br>
 * 여러번 실행시키는 개념임<br>
 * "_index_[element명]"으로 현재 foreach loop의 index를 알 수 있음<br>
 * <br>
 * list 속성 : 반복 수행할 List, java.lang.List type이어야함<br>
 * element 속성 : foreach 문 내에서 사용할 list의 각 요소 이름을 지정<br>
 * <pre>
 * ex)
 * &lt;foreach element="info" list="infos"&gt;
 * |   ${info}
 * &lt;/foreach&gt;
 * </pre>
 * 
 * @author jmsohn
 */
@FormatterSpec(group="flow", tag="foreach")
public class ForeachFormatter extends AbstractFlowComponentFormatter {
	
	/** foreach loop의 index명을 만들때 element명의 전치문구 */
	private static String INDEX_PRE = "_index_";
	
	/** listExp 속성 수행하기 위한 Evaluator */
	@Getter
	@Setter
	@FormatterAttr(name="list", mandatory=true)
	private OLExp listExp;
	
	/** element 속성의 설정값 */
	@Getter
	@Setter
	@FormatterAttr(name="element", mandatory=true)
	private String element;
	
	/** list의 element가 없을 경우 수행할 대체(alt) FlowFormatter */
	@Setter(value=AccessLevel.PRIVATE)
	@Getter(value=AccessLevel.PRIVATE)
	private AltFlowFormatter altFlowFormatter;
	
	@Override
	public void addChildFormatter(Formatter formatter) throws FormatterException {
		
		if(formatter instanceof AltFlowFormatter) {
			// 대체 Flow 설정
			// 대체 Flow가 여러개이면 마지막 것만 지정됨
			this.setAltFlowFormatter((AltFlowFormatter)formatter);
		} else {
			// 기본(Basic) Flow에 추가함
			this.getBasicFlowFormatter().addChildFormatter(formatter);
		}
	}
	
	@Override
	protected void execFormat(OutputStream out, Charset charset, Map<String, Object> values) throws FormatterException {
		
		// list 속성에 설정된
		// java.lang.List object를 value container에서 가져옴
		Object obj = null;
		
		try {
			obj = this.getListExp().execute(values).pop(Object.class);
		} catch(Exception ex) {
			throw new FormatterException(this, ex);
		}
		
		if(obj == null || (obj instanceof List) == false) {
			throw new FormatterException(this, "N/A(" + this.getListExp().getScript() + " is not List Object)");
		}
		
		// list의 값을 하나씩 설정하여 수행함
		// 위에서 List 타입 체크하였기 때문에 suppress 시킴
		// TODO
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>)obj;
		
		if(list != null && list.isEmpty() == false) {
			
			// element가 있는 경우
			// element 별로 
			// FlowFormatter를 한번씩 수행함
			int index = 0;
			for(Object element : list) {
				
				try {
					values.put(INDEX_PRE + this.getElement(), index);
					values.put(this.getElement(), element);
				} catch(Exception ex) {
					throw new FormatterException(this, ex);
				}
				
				this.getBasicFlowFormatter().format(out, charset, values);
				
				values.remove(this.getElement());
				values.remove(INDEX_PRE + this.getElement());
				
				index++;
			}
		} else {
			
			// element 가 없고, alt flow가 있을 경우
			// alt flow를 수행후 결과를 추가
			if(this.getAltFlowFormatter() != null) {
				this.getAltFlowFormatter().format(out, charset, values);
			}
		}
	}
}
