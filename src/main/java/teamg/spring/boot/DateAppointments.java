package teamg.spring.boot;

import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.service.AppointmentService;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateAppointments {
    long userid;
    Calendar cal;
    AppointmentService as;
    public List<Integer> listDays;
    public DateAppointments(long userid,Calendar cal,AppointmentService as){
        this.cal=cal;
        this.as=as;
        this.userid=userid;

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
        if(as.getAllAppointmentsByUserAndDate(userid,cal.getTime()).size()>0){
            System.out.println(day+"True");
            return true;
        }
        return false;
    }
    public String getDate(){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(cal.getTime());
        return mysqlDateString;
    }
    public String getName(int day){
        cal.set(Calendar.DAY_OF_MONTH,day);
        return new SimpleDateFormat("EEEE").format(cal.getTime());
    }
    public List<Appointment> getEvent(int day){
        cal.set(Calendar.DAY_OF_MONTH,day);
        List<Appointment> appoints = as.getAllAppointmentsByUserAndDate(userid,cal.getTime());
        return appoints;
    }
}
