# MultiThreading

Multithreading is a technique where a single program runs multiple threads concurrently to perform tasks in parallel,
improving both performance and responsiveness.

## How to implement it properly?

For implementing multithreading you can't just create another thread from the code you already had to boost your app
performance. This is because you have to handle how can the threads interact with each other. For instance, you have to
restrict their access to certain shared information. If you don't handle this, you can find unexpected behaviors, as
both Threads may try to access the same information and therefore, not use the expected value. This issue is called
**race condition**

### How to solve the race condition?

For solving this, we use the `synchronized` keyword, which makes that only one thread can access that block of code at
the same time. Example:

```java
class Counter {
    int count = 0;
    final Object lock = new Object();

    void increment() {
        synchronized (lock) {
            count++; // Thread-safe
        }
    }
}
```

In the above example, the count will only be accessed by one Thread at a time (Only one thread can be using the `lock`
instance at a time).

You can also apply the synchronized block in a class instance by doing synchronized methods (Only one thread can access
that method). In these cases, the lock would be the `this` -> The Counter instance:

```java
class Counter {
    private int count = 0;

    // Synchronized method
    synchronized void increment() {
        count++;
    }

    int getCount() {
        return count;
    }
}
```

But sometimes you will also have some case scenarios where you need to wait for another thread to finish something
before continuing your path. For doing this, we need to use `Thread.wait()` and `Thread.notify()`.
The `Thread.wait()` method waits for another thread also located in the same lock to notify him that he can continue his
path.

Please note that when one Thread is waiting, it releases the lock so that another Thread can access the shared
information while that thread is slept (waiting for notification).

This behavior can be difficult to be managed, since you may find situations where one Thread is waiting for another to
notify him, but if the other Thread ends up waiting for another notification, both of the Threads would be waiting
forever. This is known as a **deadlock**, and you have to be really careful with your code for avoiding these issues.

---

Please note that you have examples of MultiThreading inside this folder, you can check them out for some real examples!