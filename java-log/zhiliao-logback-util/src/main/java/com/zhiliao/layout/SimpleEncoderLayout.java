package com.zhiliao.layout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

public class SimpleEncoderLayout extends LayoutBase<ILoggingEvent> {
	
	@Override
	public String doLayout(ILoggingEvent event) {
		StringBuffer sbuf = new StringBuffer();
	    sbuf.append(event.getFormattedMessage());
	    sbuf.append(CoreConstants.LINE_SEPARATOR);
	    return sbuf.toString();
	}

}
