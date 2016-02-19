package org.kiranmohan.lamport.clock.message;


/**
 * Generated from IDL interface "IMessage".
 *
 * @author JacORB IDL compiler V 3.7
 * @version generated at 19 Feb, 2016 10:19:44 PM
 */

public class _IMessageStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.kiranmohan.lamport.clock.message.IMessage
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:MessageModule/IMessage:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.kiranmohan.lamport.clock.message.IMessageOperations.class;
	public java.lang.String message(java.lang.String text, int clockValue)
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "message", true);
					java.lang.String tmpResult0 = text;
_os.write_string( tmpResult0 );
					_os.write_long(clockValue);
					_is = _invoke(_os);
					java.lang.String _result = _is.read_string();
					return _result;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "message", _opsClass );
			if( _so == null )
				continue;
			IMessageOperations _localServant = (IMessageOperations)_so.servant;
			java.lang.String _result;
			try
			{
				_result = _localServant.message(text,clockValue);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

}
