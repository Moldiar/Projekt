package mpr.proj.pedigree;

import java.sql.Date;

public class DateOfBirth {
    private Date date;
    private Boolean yearOnly;

    public DateOfBirth(Date data)
    {
            this.date = data;
    }
    public void setDate(Date date, Boolean yearOnly) {
        this.date = date;
        this.yearOnly = yearOnly;
    }
    public Date getDate() {
        
            return this.date;
        }
    
}

