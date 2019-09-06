package by.devincubator.firstTask.service;

public enum ServiceType {
    USER_OPERATIONS (new UserOperationsService());

    private AbstractService service;


    ServiceType(AbstractService service) {
        this.service = service;
    }

    public AbstractService getService(){
        return service;
    }
}
