package com.srjf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class Runner {
    List<Process> processes = new ArrayList<>();
    List<Integer> arrivalBreak = new ArrayList<>();
    List<Process> activeProcesses = new ArrayList<>();
    List<Boolean> startCheck = new ArrayList<>();

    int processCount = 0;
    int timeline = 0;

    public void inputValue() {

        System.out.println("Select Number of Process:");
        Scanner myScanner = new Scanner(System.in);
        processCount = myScanner.nextInt();

        for(int i=0; i<processCount;i++){
            System.out.println("Please enter the Burst time of the process "+i+": ");
            int burstTime = myScanner.nextInt();
            System.out.println("Please enter the Arrival time of the process "+i+": ");
            int arrivalTime = myScanner.nextInt();

            Process p = new Process();
            timeline += burstTime;
            p.setTime(burstTime);
            p.setArrivalTime(arrivalTime);
            p.setRemainingTime(burstTime);
            p.setId(i);
            processes.add(p);
        }

        processes.sort(new Comparator <Process> (){
            @Override
            public int compare(Process p1, Process p2){
                return p1.getArrivalTime()-p2.getArrivalTime();
            }
        });

        timeline += processes.get(0).getArrivalTime();
        for(int i=0; i<processes.size();i++){
            if(!arrivalBreak.contains(processes.get(i).getArrivalTime())){
                arrivalBreak.add(processes.get(i).getArrivalTime());
            }
            startCheck.add(false);
        }
    }

    public void runProcesses() throws InterruptedException {

        int tempStart = processes.get(0).getArrivalTime();
        int tempEnd;
        System.out.println(tempStart+"tempStart");

        int arrivalBreakP = 1;
        boolean mainLoop = true;
        boolean subLoop = true;
        int progress = processes.get(0).getArrivalTime();
        System.out.println(progress+"progress");

        while (mainLoop){

            for(int i=0;i<processes.size();i++){
                if((processes.get(i).getArrivalTime()<=progress) && (processes.get(i).getRemainingTime()>0)){
                    activeProcesses.add(processes.get(i));
                }
            }
            for(int i=0;i<activeProcesses.size();i++){
                System.out.print(activeProcesses.get(i).getId()+"ActiveProcesses");
            }
            System.out.println();

            activeProcesses.sort(new Comparator<Process>(){
                @Override
                public int compare(Process p1, Process p2){
                    return p1.getTime()-p2.getTime();
                }
            });
            for(int i=0;i<activeProcesses.size();i++){
                System.out.print(activeProcesses.get(i).getId()+"ActiveProcessesafterSort");
            }
            System.out.println();
            int temp1;
            int temp2;
            if(arrivalBreakP>=arrivalBreak.size()){
                temp1=progress;
                temp2=temp1+activeProcesses.get(0).getRemainingTime();
            }else{
                temp1=progress;
                temp2=arrivalBreak.get(arrivalBreakP);
            }


            System.out.println(temp1+"temp1");
            System.out.println(temp2+"temp2");

            while (subLoop){

                if(!startCheck.get(activeProcesses.get(0).getId())){
                    System.out.println(startCheck.get(activeProcesses.get(0).getId())+"startTimeboolean");
                    System.out.println(activeProcesses.get(0).getStartTime()+"startTime");
                    activeProcesses.get(0).setStartTime(progress);
                    startCheck.set(activeProcesses.get(0).getId(),true);
                    System.out.println(activeProcesses.get(0).getStartTime()+"startTimeafter");
                    System.out.println(startCheck.get(activeProcesses.get(0).getId())+"startTimebooleanafter");
                }
                int tempRemain = activeProcesses.get(0).getRemainingTime();
                System.out.println(activeProcesses.get(0).getRemainingTime()+"RemainingTimebefore");
                if(activeProcesses.get(0).getRemainingTime()>=(temp2-temp1)){
                    activeProcesses.get(0).setRemainingTime(((activeProcesses.get(0).getRemainingTime()-(temp2-temp1))));
                    System.out.println(activeProcesses.get(0).getRemainingTime()+"RemainingTimeafter");
                }else{
                    activeProcesses.get(0).setRemainingTime((activeProcesses.get(0).getRemainingTime()-(activeProcesses.get(0).getRemainingTime())));
                    temp2 = temp1+tempRemain;
                }

                if(activeProcesses.get(0).getRemainingTime() == 0){
                    System.out.println("endof Remain");
                    activeProcesses.get(0).setEndTime(progress);
                }
                progress += temp2-temp1;
                if(progress==timeline){
                    break;
                }
                System.out.println(progress+"insideLoop");
                if(arrivalBreak.contains(progress)){
                    System.out.println("whileloop sub end");
                    arrivalBreakP +=1;
                    break;
                }else{
                    temp1=progress;
                    System.out.println(temp1+"Else statement temp1");
                    if(arrivalBreakP>=arrivalBreak.size()){
                        temp2=temp1+activeProcesses.get(1).getRemainingTime();
                        System.out.println(temp2+"Else statement temp2");
                    }else{
                        temp2=arrivalBreak.get(arrivalBreakP);
                        System.out.println(temp2+"Else statement temp2");
                    }

                    activeProcesses.remove(0);
                }
                activeProcesses.sort(new Comparator<Process>(){
                    @Override
                    public int compare(Process p1,Process p2){
                        return p1.getTime() - p2.getTime();
                    }
                });
            }
            activeProcesses.clear();
            if(progress==timeline){
                break;
            }

        }
    }

    public void printResult() {
        System.out.println("------------------------------------");
        System.out.println("Id   Time   ArrivalT   WaitT   StartT    EndT");
        for(int i=0;i<processes.size();i++){
            System.out.print(processes.get(i).getId());
            System.out.print("   ");
            System.out.print(processes.get(i).getTime());
            System.out.print("   ");
            System.out.print(processes.get(i).getArrivalTime());
            System.out.print("   ");
            System.out.print(processes.get(i).getWaitTime());
            System.out.print("   ");
            System.out.print(processes.get(i).getStartTime());
            System.out.print("   ");
            System.out.print(processes.get(i).getEndTime());
            System.out.println();
        }
        System.out.println("------------------------------------");
        // TODO: Print the result accordingly
    }
}