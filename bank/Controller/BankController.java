package com.example.bank.Controller;

import com.example.bank.Api.ApiResponse;
import com.example.bank.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/customer")
public class BankController {
    ArrayList<Customers> Customer=new ArrayList<>();
    @GetMapping("/get")
    public ArrayList<Customers> getTasks(){
        return Customer;
    }
    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customers c){
        Customer.add(c);
        return new ApiResponse("customer added",200);
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index,@RequestBody Customers c){
        Customer.set(index,c);
        return new ApiResponse("customer updated",200);
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        Customer.remove(index);
        return new ApiResponse("customer deleted",200);
    }
    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index,@RequestBody double amount){
       if(amount>0){
           Customer.get(index).setBalance(Customer.get(index).getBalance()+amount);
           return new ApiResponse("deposit made",200);
       }
        else return new ApiResponse("Enter correct number",404);
    }
    @PutMapping("/withdraw/{index}")
    public ApiResponse withdraw(@PathVariable int index,@RequestBody double amount){
        if(amount>0&&amount<=Customer.get(index).getBalance()){
            Customer.get(index).setBalance(Customer.get(index).getBalance()-amount);
            return new ApiResponse("withdraw made",200);
        }
        else return new ApiResponse("Enter correct number",404);
    }
}
