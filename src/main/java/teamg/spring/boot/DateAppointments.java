package teamg.spring.boot;

import teamg.spring.boot.service.AppointmentService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateAppointments {
    public static int HARDCODED_USERID = 9;
    Calendar cal;
    AppointmentService as;
    public List<Integer> listDays;
    public DateAppointments(Calendar cal,AppointmentService as){
        this.cal=cal;
        this.as=as;

    }
    public List<Integer> getListDays(){
        listDays = new ArrayList<>();
        for(int i=1;i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
            listDays.add(i);
        }
        return listDays;
    }
    public void setMonth(int month){
        cal.set(Calendar.MONTH,month);
    }
    public boolean hasEvent(int day){
        cal.set(Calendar.DAY_OF_MONTH,day);
        if(as.getAllAppointmentsByUserAndDate(HARDCODED_USERID,cal.getTime()).size()>0){
            System.out.println(day+"True");
            return true;
        }
        return false;
    }
}
