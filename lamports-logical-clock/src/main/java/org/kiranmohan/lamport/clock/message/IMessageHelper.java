package org.kiranmohan.lamport.clock.message;


/**
 * Generated from IDL interface "IMessage".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at 19 Feb, 2016 10:19:44 PM
 */

public abstract class IMessageHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IMessageHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:MessageModule/IMessage:1.0", "IMessage");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.kiranmohan.lamport.clock.message.IMessage s)
	{
			any.insert_Object(s);
	}
	public static org.kiranmohan.lamport.clock.message.IMessage extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:MessageModule/IMessage:1.0";
	}
	public static IMessage read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.kiranmohan.lamport.clock.message._IMessageStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.kiranmohan.lamport.clock.message.IMessage s)
	{
		_out.write_Object(s);
	}
	public static org.kiranmohan.lamport.clock.message.IMessage narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.kiranmohan.lamport.clock.message.IMessage)
		{
			return (org.kiranmohan.lamport.clock.message.IMessage)obj;
		}
		else if (obj._is_a("IDL:MessageModule/IMessage:1.0"))
		{
			org.kiranmohan.lamport.clock.message._IMessageStub stub;
			stub = new org.kiranmohan.lamport.clock.message._IMessageStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.kiranmohan.lamport.clock.message.IMessage unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.kiranmohan.lamport.clock.message.IMessage)
		{
			return (org.kiranmohan.lamport.clock.message.IMessage)obj;
		}
		else
		{
			org.kiranmohan.lamport.clock.message._IMessageStub stub;
			stub = new org.kiranmohan.lamport.clock.message._IMessageStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
