package ru.codewars;

import lombok.extern.java.Log;
import ru.TaskSolution;

@Log
public abstract class CodeWarsTaskSolution<ResultType, ArgType> extends TaskSolution<ResultType, ArgType> {
    public abstract int getTaskLvl();
}
