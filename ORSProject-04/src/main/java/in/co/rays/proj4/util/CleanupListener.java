package in.co.rays.proj4.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Collections;

@WebListener
public class CleanupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 1. Close the c3p0 pool
        try {
            JDBCDataSource.closePool();
        } catch (Throwable ignored) {
        }

        // 2. Stop MySQL's cleanup thread
        try {
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.uncheckedShutdown();
        } catch (Throwable ignored) {
        }

        // 3. Deregister JDBC drivers loaded by this webapp
        ClassLoader cl = getClass().getClassLoader();
        for (Driver d : Collections.list(DriverManager.getDrivers())) {
            if (d.getClass().getClassLoader() == cl) {
                try {
                    DriverManager.deregisterDriver(d);
                } catch (Exception ignored) {
                }
            }
        }
    }
}