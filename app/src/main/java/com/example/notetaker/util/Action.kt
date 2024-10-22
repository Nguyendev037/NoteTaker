package com.example.notetaker.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}


fun String?.toAction() : Action {
    when (this) {
        "ADD" -> {
            return Action.ADD;
        }
        "UPDATE" -> {
            return Action.UPDATE;
        }
        "DELETE" -> {
            return Action.DELETE;
        }
        "DELETE_ALL" -> {
            return Action.DELETE_ALL;
        }
        "UNDO" -> {
            return Action.UNDO;
        }
        else -> {
            return Action.NO_ACTION;
        }
    }

}