Notifications Example: Entity Inheritance and Strategy Pattern

## Introduction

Demonstrates how to use JPA inheritance as a means to implement the
Strategy design pattern.

## Domain Model

In our domain we have a notification system that needs to send both Email
and SMS to recipients. To do this we implement the
`SmsNotification` and `EmailNotification` classes to inherit the base
class `Notification`.

We then use the `JOINED` table inheritance strategy to persist these
entities to the database.

## Services

For the purpose of sending the notifications, we implement the
`NotificationSender` component for each type of notification:
`SmsNotificationSender` and `EmailNotificationSender`. (The actual
sending logic is not implemented but this is sufficient for
understanding how the strategy pattern works.)

The client does not interact with the `NotificationSender` directly
since they should only be concerned with sending messages to recipients,
and leave the underlying system to figure out the subscriber
channels each client has opted for.

Therefore, we can use the *Facade Pattern* to expose a very simple API
to send messages to recipients.

To make this work, we:

- make use of the `List` auto-wiring feature provided by Spring to
  inject any `NotificationSender` that is configured into our system
  into our `NotificationService` implementation.
- The `init` method builds a map from the kind of sending the sender
  supports to the instance used for sending.

Because JPA supports polymorphic queries we are able to load different
types of `Notification`s from our database in a single query.

## Conclusion

Entity inheritance is a very useful technique, but mostly when you used
with a behavioral software design pattern, such as `Strategy` or `Visitor`
pattern.

If you only need to propagate certain properties from a base class to
all subclasses, you don’t need JPA entity inheritance. All you need is
the `@MappedSuperclass` annotation, but that’s not entity inheritance
since the object hierarchy is only visible in the OOP domain, not in the
relationship model.

----

Contact: <henry.g.brown@gmail.com>