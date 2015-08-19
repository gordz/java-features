//import java.util.Set;
//
///**
// * Example of the builder pattern - using a builder to churn out instances of a particular object.
// * 
// * In this instance, there is a static factory method on the class being instantiated to return a new builder.
// * 
// * @author graham
// *
// */
//
//public class User {
//	private String fullName;
//	private String username;
//	private Set<EmailAddress> emailAddresses;
//	private String signature;
//	private URL website;
//	private Set<User> contacts;
//
//	private User() {
//	}
//
//	public static IFullName builder() {
//		return new Builder();
//	}
//
//	public interface IFullName {
//		IUsername fullName(final String fullName);
//	}
//
//	public interface IUsername {
//		IEmailAddresses username(final String username);
//	}
//
//	public interface IEmailAddresses {
//		IBuild emailAddresses(final Set<EmailAddress> emailAddresses);
//	}
//
//	public interface IBuild {
//		User build();
//
//		IBuild signature(String signature);
//
//		IBuild website(URL website);
//
//		IBuild contacts(Set<User> contacts);
//	}
//
//	private static class Builder implements IFullName, IUsername,
//			IEmailAddresses, IBuild {
//		private User instance = new User();
//
//		public IUsername fullName(final String fullName) {
//			validateFullName(fullName);
//			instance.fullName = fullName;
//			return this;
//		}
//
//		public IEmailAddresses username(final String username) {
//			validateUsername(username);
//			instance.username = username;
//			return this;
//		}
//
//		public IBuild emailAddresses(final Set<EmailAddress> emailAddresses) {
//			validateEmailAddresses(emailAddresses);
//			instance.emailAddresses = emailAddresses;
//			return this;
//		}
//
//		public IBuild signature(final String signature) {
//			validateSignature(signature);
//			instance.signature = signature;
//			return this;
//		}
//
//		public IBuild website(final URL website) {
//			validateWebsite(website);
//			instance.website = website;
//			return this;
//		}
//
//		public IBuild contacts(final Set<User> contacts) {
//			validateContacts(contacts);
//			instance.contacts = contacts;
//			return this;
//		}
//
//		public User build() {
//			return instance;
//		}
//	}
//
//	private static void validateFullName(final String fullName) {
//		throw new IllegalArgumentException();
//	}
//
//	private static void validateUsername(final String username) {
//		throw new IllegalArgumentException();
//	}
//
//	private static void validateEmailAddresses(
//			final Set<EmailAddress> emailAddresses) {
//		throw new IllegalArgumentException();
//	}
//
//	private static void validateSignature(final String signature) {
//		throw new IllegalArgumentException();
//	}
//
//	private static void validateWebsite(final URL website) {
//		throw new IllegalArgumentException();
//	}private static void validateContacts(final Set<User> contacts){throw new IllegalArgumentException();}}