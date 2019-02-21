package com.zhiliao.demo.layout;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

public class MyLayout01 extends LayoutBase<ILoggingEvent> {

	@Override
	public String doLayout(ILoggingEvent event) {
		StringBuffer sbuf = new StringBuffer(128);
		sbuf.append("我是自定义Layout打印日志：");
	    sbuf.append(event.getTimeStamp());
	    sbuf.append(" ");
	    sbuf.append(event.getLevel());
	    sbuf.append(" [");
	    sbuf.append(event.getThreadName());
	    sbuf.append("] ");
	    sbuf.append(event.getLoggerName());
	    sbuf.append(" - ");
	    sbuf.append(event.getFormattedMessage());
	    sbuf.append(CoreConstants.LINE_SEPARATOR);
	    return sbuf.toString();
	}

}
