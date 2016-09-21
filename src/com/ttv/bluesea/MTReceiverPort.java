/**
 * MTReceiverPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ttv.bluesea;

public interface MTReceiverPort extends java.rmi.Remote {
    public int receiveMO(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, java.lang.String string3, java.lang.String string4, java.lang.String string5, java.lang.String string6) throws java.rmi.RemoteException;
    public int sendMT(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, java.lang.String string3, java.lang.String string4, java.lang.String string5, java.lang.String string6, java.lang.String string7, java.lang.String string8) throws java.rmi.RemoteException;
    public int sendMTEx(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, java.lang.String string3, java.lang.String string4, java.lang.String string5, java.lang.String string6, java.lang.String string7, java.lang.String string8, java.lang.String string9) throws java.rmi.RemoteException;
    public int sendMTcheckSum(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, java.lang.String string3, java.lang.String string4, java.lang.String string5, java.lang.String string6, java.lang.String string7, java.lang.String string8) throws java.rmi.RemoteException;
    public int sendRequest(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, java.lang.String string3) throws java.rmi.RemoteException;
    public int smsserviceMO(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2) throws java.rmi.RemoteException;
    public int spamMT7077(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, java.lang.String string3, java.lang.String string4, java.lang.String string5) throws java.rmi.RemoteException;
    public java.lang.String getLatestUsers(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, int intVal) throws java.rmi.RemoteException;
    public java.lang.String sendMTs(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2, java.lang.String string3) throws java.rmi.RemoteException;
    public java.lang.String sendSms(java.lang.String string, java.lang.String string0, java.lang.String string1, java.lang.String string2) throws java.rmi.RemoteException;
}
