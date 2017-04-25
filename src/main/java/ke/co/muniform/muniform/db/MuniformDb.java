package ke.co.muniform.muniform.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by  on 18/04/2017.
 */
@Table(name = "MuniformDb")
public class MuniformDb extends Model{

    @Column(name = "data")
    String data;

    @Column(name = "createdat")
    String createdat;

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MuniformDb() {
        super();
    }

    public static List<MuniformDb> getAll() {
        return new Select()
                .from(MuniformDb.class)
                .execute();
    }
}
