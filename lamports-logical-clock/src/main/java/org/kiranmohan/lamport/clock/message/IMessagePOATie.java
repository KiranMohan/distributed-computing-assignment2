package org.kiranmohan.lamport.clock.message;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "IMessage".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at 19 Feb, 2016 1:30:06 PM
 */

public class IMessagePOATie
	extends IMessagePOA
{
	private IMessageOperations _delegate;

	private POA _poa;
	public IMessagePOATie(IMessageOperations delegate)
	{
		_delegate = delegate;
	}
	public IMessagePOATie(IMessageOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.kiranmohan.lamport.clock.message.IMessage _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.kiranmohan.lamport.clock.message.IMessage __r = org.kiranmohan.lamport.clock.message.IMessageHelper.narrow(__o);
		return __r;
	}
	public org.kiranmohan.lamport.clock.message.IMessage _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.kiranmohan.lamport.clock.message.IMessage __r = org.kiranmohan.lamport.clock.message.IMessageHelper.narrow(__o);
		return __r;
	}
	public IMessageOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IMessageOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public java.lang.String message(java.lang.String text)
	{
		return _delegate.message(text);
	}

}
