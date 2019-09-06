package by.devincubator.firstTask.controller.command.commandimp;

import by.devincubator.firstTask.controller.command.Command;
import by.devincubator.firstTask.service.ServiceFactory;
import by.devincubator.firstTask.service.ServiceType;
import by.devincubator.firstTask.service.UserOperationsService;

import javax.servlet.http.HttpServletRequest;

public class GetRichestUserCommand implements Command {
    public static final String CURRENT_PAGE = "/view/main_page.jsp";

    public String execute(HttpServletRequest request) {
        UserOperationsService userOperationsService = (UserOperationsService) ServiceFactory
                .getService(ServiceType.USER_OPERATIONS);
        request.setAttribute("richest_user", userOperationsService.findRichestUser());

        return CURRENT_PAGE;
    }
}
