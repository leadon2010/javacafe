package common;

public class Deadlock {
	static class Friend {
		private final String name;

		public Friend(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public synchronized void bow(Friend bower) {
			System.out.format("%s: %s. " + "  has bowed to me!%n", this.name, bower.getName());
			bower.bowBack(this);
		}

		public synchronized void bowBack(Friend bower) {
			System.out.format("%s: %s. " + " has bowed back to me!%n", this.name, bower.getName());
		}
	}

	public static void main() {
		System.out.println("start");
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		/* Thread tha = */new Thread(new Runnable() {
			public void run() {
				alphonse.bow(gaston);
			}
		}).start();
//		tha.setName("AAA");
//		System.out.println(tha.getName());
//		tha.start();
		/* Thread thb = */new Thread(new Runnable() {
			public void run() {
				gaston.bow(alphonse);
			}
		}).start();
//		thb.setName("BBB");
//		System.out.println(thb.getName());
//		thb.start();
		System.out.println("end");
	}
}
