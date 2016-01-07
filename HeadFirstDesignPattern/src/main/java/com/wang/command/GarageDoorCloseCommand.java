package com.wang.command;

public class GarageDoorCloseCommand implements Command{
	GarageDoor door;

	public GarageDoorCloseCommand(GarageDoor door) {
		this.door = door;
	}

	@Override
	public void execute() {
		door.down();
	}

	@Override
	public void undo() {
		door.up();
	}

}
