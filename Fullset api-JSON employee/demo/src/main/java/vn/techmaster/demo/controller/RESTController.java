// Thư viện
package vn.techmaster.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.model.Book;
import vn.techmaster.demo.reponsitory.DAO;

@RestController// Trả về dạng JSON
@RequestMapping("/api/books") // Đường dẫn
public class RESTController {
    @Autowired // Đánh dấu để Spring inject một đối tượng BookDAO vào đây
    private DAO<Book> bookDAO;

    @GetMapping("")// Lấy ra toàn bộ sách
    public ResponseEntity<List<Book>> getListBook() {//Respose trả về các mã lỗi
        return ResponseEntity.status(HttpStatus.OK).body(bookDAO.getall());// Trả về các trạng thái và body nếu đúng thì trả về danh sach
    }

    @GetMapping("/{id}")// Lấy ra sách theo id
    public ResponseEntity<Book> getBook(@PathVariable int id) {// @PathVariable lấy ra thông tin trong URL
        Optional<Book> bookOptional = bookDAO.get(id);// lấy ra trong csdl thành phần trùng id
        Book book = null;
        if (bookOptional.isPresent()) {// Hàm isPresent() kiểm tra xem trong optional có giá trị không
            book = bookOptional.get(); // -> Nếu có thì dùng hàm get() để lấy giá trị ra
        }
        return ResponseEntity.ok().body(book);// Trả về trạng thái và giá trị lấy đc
    }

    @PostMapping("")// Tạo ra sách mới
    public ResponseEntity<List<Book>> createBook(@RequestParam int id, @RequestParam String title,
            @RequestParam String description) {// @RequestParam dùng để đánh dấu một biến là request param trong request gửi lên server.
                                                //Nó sẽ gán dữ liệu của param-name tương ứng vào biến
        Book newBook = new Book(id, title, description); // Tạo ra 1 đối tượng mới
        bookDAO.add(newBook);// Thêm vào danh sách
        List<Book> listBook = bookDAO.getall();// Lấy toàn bộ danh sách mới
        return ResponseEntity.status(HttpStatus.CREATED).body(listBook);// Trả ra kết quả
    }

    @PutMapping("")// Sửa đổi thông tin sách- tương tự như thêm sách tuy  nhiên hàm update xử lí khác
    public ResponseEntity<List<Book>> updateBook(@RequestParam int id, @RequestParam String title,
            @RequestParam String description) {
        Book newBook = new Book(id, title, description);
        bookDAO.update(newBook);
        return ResponseEntity.status(HttpStatus.OK).body(bookDAO.getall());
    }

    @DeleteMapping("/{id}")// Xóa sách theo id đã chọn - xử lí giống hàm get tuy nhiên khác cách xử lí của hàm delete
    public ResponseEntity<List<Book>> deleteBook(@PathVariable int id) {
        bookDAO.deleteByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDAO.getall());//Trả về list book như hàm tạo ra danh sách mới
    }
}
