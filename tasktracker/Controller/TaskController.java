package com.example.tasktracker.Controller;

import com.example.tasktracker.Api.ApiResponse;
import com.example.tasktracker.Model.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    ArrayList<Task> Tasks=new ArrayList<>();
    @GetMapping("/get")
    public ArrayList<Task> getTasks(){
        return Tasks;
    }
    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        Tasks.add(task);
        return new ApiResponse("task added",200);
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index,@RequestBody Task task){
        Tasks.set(index,task);
        return new ApiResponse("task updated",200);
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        Tasks.remove(index);
        return new ApiResponse("task deleted",200);
    }
    @PutMapping("/change/{index}")
    public ApiResponse changeStatus(@PathVariable int index){
        if(Tasks.get(index).getStatus().equals("done"))
        {
        Tasks.get(index).setStatus("not done"); }
        else Tasks.get(index).setStatus("done");
        return new ApiResponse("status changed",200);
}
    @GetMapping("/search/{title}")
    public ApiResponse search(@PathVariable String title){
     for(Task task:Tasks){
         if(task.getTitle().equals(title)) {
             return new ApiResponse("the task is : "+task,200);
         }
     }
     return new ApiResponse("not found",404);
    }

}
