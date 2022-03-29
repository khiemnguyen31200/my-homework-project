package vn.techmaster.demo.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.techmaster.demo.model.Book;

@Service
public class BookDAO extends DAO<Book> { // Mở rộng class DAO

    public BookDAO() {// Khởi tạo BookDao bằng cách cho thêm các dữ liệu vào 
        listObject.add(new Book(1, "Không gia đình", "Chú bé Remy lang thang theo gánh xiếc của bác Vitaly"));
        listObject.add(new Book(2, "Cuốn theo chiều gió", "Nội chiến Hoa kỳ, cuộc tình giữa Red Butler và Ohara"));
        listObject.add(new Book(3, "Dế Mèn phiêu lưu ký", "Tô Hoài"));
    }

    @Override //Ghi đè phương thức lấy sách 
    public List<Book> getall() {
        return listObject;// trả về danh sách sách đã được tạo từ Dao.java và vừa được thêm dữ liệu ở hàm trên
    }

    @Override
    public Optional<Book> get(int id) {
        return listObject.stream().filter(p -> (p.getId() == id)).findFirst();//chọn trong danh sách phần tử có thuộc tính id trùng với id đã cho ở đề bài và nếu có nhiều phần tử thì sẽ lấy ra phần tử đầu tiên
    }

    @Override
    public void add(Book t) {
        listObject.add(t);
    }

    @Override
    public void update(Book t) {
        Optional<Book> matchBook = listObject.stream().filter(p -> (p.getId() == t.getId())).findFirst(); // Tìm ra đối tượng có id trùng với id ở @requestPram
        if (matchBook.isPresent()) {// Kiểm tra xem trong optional có tồn tại không -> Nếu có thực hiện các hàm bên dưới
            Book book = matchBook.get();// Lấy ra đối tượng tồn tại trong optional
            int index = listObject.indexOf(book);// Tìm ra vị trí sách 
            listObject.set(index, t);// Tìm đến index đó và cài đặt những giá trị vừa sửa đổi vào <t là một đối tượng của book>
        } else {
            listObject.add(t);
        }
    }

    @Override
    public void deleteByID(int id) {
        Optional<Book> match = listObject.stream().filter(p -> (p.getId() == id)).findFirst();
        if (match.isPresent()) {
            Book book = match.get();
            listObject.remove(book);
        }
    }

    @Override
    public void delete(Book t) {
        listObject.remove(t);
    }

}
