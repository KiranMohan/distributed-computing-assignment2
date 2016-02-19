package org.kiranmohan.lamport.clock.message;

/**
 * Generated from IDL interface "IMessage".
 *
 * @author JacORB IDL compiler V 3.5
 * @version generated at 19 Feb, 2016 1:30:06 PM
 */

public final class IMessageHolder	implements org.omg.CORBA.portable.Streamable{
	 public IMessage value;
	public IMessageHolder()
	{
	}
	public IMessageHolder (final IMessage initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return IMessageHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IMessageHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		IMessageHelper.write (_out,value);
	}
}
