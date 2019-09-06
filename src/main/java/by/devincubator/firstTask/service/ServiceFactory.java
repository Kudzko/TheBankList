package by.devincubator.firstTask.service;

public class ServiceFactory {
    public static AbstractService getService(ServiceType serviceType) {
        return serviceType.getService();
    }
}
