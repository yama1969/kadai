package local.dao;

import local.beans.Item;
import local.exceptions.CannotSearchItemsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ItemDataAccessor{
    private final JdbcTemplate jdbc;

    @Autowired
    public ItemDataAccessor(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    /***************************************************************************
     商品検索
    ***************************************************************************/
    public ArrayList<Item> select(Item key) throws CannotSearchItemsException {
        ArrayList<Item> result = new ArrayList<Item>();

        try {
            String sql = "SELECT code, name, price FROM items WHERE code LIKE ? AND name LIKE ?";

            //条件設定
            String code = key.getCode();
            if (code == null) {
                code = "";
            }
            code = "%" + code + "%";

            String name = key.getName();
            if (name == null) {
                name = "";
            }
            name = "%" + name + "%";

            //検索実行
            List<Map<String, Object>> rs = jdbc.queryForList(sql, code, name);
            for (Map<String, Object> row : rs) {
                String codedat = row.get("code").toString();
                String namedat = row.get("name").toString();
                int pricedat = Integer.parseInt(row.get("price").toString());
                result.add(new Item(codedat, namedat, pricedat));
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new CannotSearchItemsException("検索できませんでした。やり直してください。");
        }
        return result;
    }
}
