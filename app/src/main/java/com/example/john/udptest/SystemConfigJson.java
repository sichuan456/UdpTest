package com.example.john.udptest;

import java.util.List;

/**
 * Created by John on 2015/11/20.
 */
public class SystemConfigJson {
    private boolean IsSuccess;
    private String MethodName;
    private List<SystemConfigData> Data;
	public boolean isIsSuccess() {
		return IsSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		IsSuccess = isSuccess;
	}
	public String getMethodName() {
		return MethodName;
	}
	public void setMethodName(String methodName) {
		MethodName = methodName;
	}
	public List<SystemConfigData> getData() {
		return Data;
	}
	public void setData(List<SystemConfigData> data) {
		Data = data;
	}

    

    
}
