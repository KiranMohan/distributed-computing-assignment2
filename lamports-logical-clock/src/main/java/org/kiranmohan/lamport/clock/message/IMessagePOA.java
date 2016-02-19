package org.kiranmohan.lamport.clock.message;


/**
 * Generated from IDL interface "IMessage".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at 19 Feb, 2016 10:19:44 PM
 */

public abstract class IMessagePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.kiranmohan.lamport.clock.message.IMessageOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "message", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:MessageModule/IMessage:1.0"};
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
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // message
			{
				java.lang.String _arg0=_input.read_string();
				int _arg1=_input.read_long();
				_out = handler.createReply();
				java.lang.String tmpResult1 = message(_arg0,_arg1);
_out.write_string( tmpResult1 );
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
