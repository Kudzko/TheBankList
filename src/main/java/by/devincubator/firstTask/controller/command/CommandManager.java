package by.devincubator.firstTask.controller.command;

import by.devincubator.firstTask.controller.command.commandimp.DefaultCommand;
import by.devincubator.firstTask.controller.command.commandimp.GetRichestUserCommand;
import by.devincubator.firstTask.controller.command.commandimp.GetSumAllAccountsCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    public enum CommandType {
        GET_RICHEST_USER, GET_SUM_ALL_ACCOUNTS, DEFAULT_COMMAND;
    }

    private static Map<CommandType, Command> commandMap;

    static {
        commandMap = new HashMap<CommandType, Command>();
        commandMap.put(CommandType.DEFAULT_COMMAND, new DefaultCommand());
        commandMap.put(CommandType.GET_RICHEST_USER, new GetRichestUserCommand());
        commandMap.put(CommandType.GET_SUM_ALL_ACCOUNTS, new GetSumAllAccountsCommand());
    }

    private CommandManager() {
    }

    public static Command getCommand(String command) {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT_COMMAND;
        }
        return commandMap.get(commandType);
    }


}
