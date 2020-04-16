package com.curso.cursomc.resources.exception;

public class StandardError {

	private static final long serialVersionUID = 1L;
		public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}
		private Integer status;
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Long getTimeStamp() {
			return timeStamp;
		}
		public void setTimeStamp(Long timeStamp) {
			this.timeStamp = timeStamp;
		}
		private String msg;
		private Long timeStamp;
}
