package com.zhiliao.demo.layout;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

public class MyLayout02 extends LayoutBase<ILoggingEvent> {
	
	private String contextName;
	private String userName;
	
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public String doLayout(ILoggingEvent event) {
		StringBuffer sbuf = new StringBuffer(128);
		sbuf.append("我是自定义Layout打印日志：");
		sbuf.append(" contextName = ").append(contextName);
		sbuf.append(" ");
		sbuf.append(" userName = ").append(userName);
		sbuf.append(" ");
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
