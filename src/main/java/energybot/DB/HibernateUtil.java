package energybot.DB;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				//Configuration configuration = new Configuration();

				Configuration configuration =   new Configuration().configure();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://id:port/energy?createDatabaseIfNotExist=TRUE");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "password");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "update");

				// c3p0 configuration
				settings.put(Environment.C3P0_MIN_SIZE, 5); // Minimum size of pool
				settings.put(Environment.C3P0_MAX_SIZE, 20); // Maximum size of pool
				settings.put(Environment.C3P0_ACQUIRE_INCREMENT, 1); // Number of connections acquired at a time when
																		// pool is exhausted
				settings.put(Environment.C3P0_TIMEOUT, 1800); // Connection idle time
				settings.put(Environment.C3P0_MAX_STATEMENTS, 150); // PreparedStatement cache size

				settings.put(Environment.C3P0_CONFIG_PREFIX + ".initialPoolSize", 5);

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(EnergyInfo.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
}
