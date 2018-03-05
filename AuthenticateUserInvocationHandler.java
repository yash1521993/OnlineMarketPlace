// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// yashkuru

/*
	AuthenticateUserInvocationHandler is responsible for implementing invocation hanlder
	contains a method invoke with three args
*/

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AuthenticateUserInvocationHandler implements InvocationHandler, Serializable {
	private Object objectImpl;

	//constructor with object initialization
	public AuthenticateUserInvocationHandler(Object impl) {
		this.objectImpl = impl;
	}

	//overriding superclass's Object 
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//reflection implementation
		if (method.isAnnotationPresent(AnnotateInterface.class)) {
			//get annotation value using reflection
			AnnotateInterface test = method.getAnnotation(AnnotateInterface.class);
			//get session value
			Session session = (Session) args[0];

			//comparing annotation value and session value
			if (session.getLoginType().equalsIgnoreCase(test.value())) {
				return method.invoke(objectImpl, args);
			} 
			else {
				//throw an user defined exception if values doesn't match
				throw new AuthenticateUserException(method.getName());
			}
		} 
		else {
			//if no annotation is present
			return method.invoke(objectImpl, args);
		}
	}
}
