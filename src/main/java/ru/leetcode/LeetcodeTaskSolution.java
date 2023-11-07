package ru.leetcode;

import lombok.extern.java.Log;
import ru.TaskSolution;

@Log
public abstract class LeetcodeTaskSolution<ResultType, ArgType> extends TaskSolution<ResultType, ArgType> {
    public abstract TaskDifficulty getTaskDifficulty();
}
