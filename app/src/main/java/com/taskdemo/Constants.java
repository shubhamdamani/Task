package com.taskdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {

    public static final List<String> company =
            Collections.unmodifiableList(Arrays.asList("DeShaw", "Google","Flipkart","Oracle","others"));

    public static final List<String> topic =
            Collections.unmodifiableList(Arrays.asList("dp", "greedy","dfs","heaps","adhoc","maths","binary search","others"));
    public static final List<String> interviewType =
            Collections.unmodifiableList(Arrays.asList("Online-test", "Interview"));
    public static final List<String> college =
            Collections.unmodifiableList(Arrays.asList("IIT-B", "MNNIT","BITS","others"));
    public static final List<String> jobType=
            Collections.unmodifiableList(Arrays.asList("Intern", "FullTime"));

}
