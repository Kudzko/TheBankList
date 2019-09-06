package by.devincubator.firstTask.controller.command.commandimp;

import by.devincubator.firstTask.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    public static final String DEFAULT_PAGE = "/view/default_page.jsp";
    public String execute(HttpServletRequest request) {
        return null;
    }
}
