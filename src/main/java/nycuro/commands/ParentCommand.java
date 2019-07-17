package nycuro.commands;

import cn.nukkit.command.Command;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public abstract class ParentCommand extends Command {

    public ParentCommand(String name, String description) {
        super(name, description);
        this.description = description;
    }
}
