package com.azz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("library")
public class Main {

    private final BookRepository bookRepository;
    private final memberRepository memberRepository;
    private final TransactionRepository transactionRepository;

    public Main(BookRepository bookRepository, memberRepository memberRepository, TransactionRepository transactionRepository){
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //Book Request
    @GetMapping("book")
    public List<Book> getBook(){
        return bookRepository.findAll();
    }

    record NewBookRequest(String title, String author, String description, String categories, Integer quantity, Integer booked){}

    @PostMapping("book")
    public void addCustomer(NewBookRequest request){
        Book book = new Book();
        book.setTitle(request.title);
        book.setAuthor(request.author);
        book.setDescription(request.description);
        book.setCategories(request.categories);
        book.setQuantity(request.quantity);
        book.setBooked(request.booked);
        bookRepository.save(book);
    }

    @DeleteMapping("book/{bookId}")
    public void deleteBook(@PathVariable("bookId") Integer id){
        bookRepository.deleteById(id);
    }

    @PutMapping("book/{bookId}")
    public void updateBook(@PathVariable("bookId") Integer id, NewBookRequest request){
        Book book = bookRepository.findById(id).get();
        book.setTitle(request.title);
        book.setAuthor(request.author);
        book.setDescription(request.description);
        book.setCategories(request.categories);
        book.setQuantity(request.quantity);
        book.setBooked(request.booked);
        bookRepository.save(book);
    }

    //member request
    @GetMapping("member")
    public List<Member> getMember(){
        return memberRepository.findAll();
    }

    record NewMemberRequest(String name, String phone,String email, String address){}

    @PostMapping("member")
    public void addMember(NewMemberRequest request){
        Member member = new Member();
        member.setName(request.name);
        member.setPhone(request.phone);
        member.setEmail(request.email);
        member.setAddress(request.address);
        memberRepository.save(member);
    }

    @DeleteMapping("member/{memberId}")
    public void deleteMember(@PathVariable("memberId") Integer id){
        memberRepository.deleteById(id);

    }

    @PutMapping("member/{memberId}")
    public void updateMember(@PathVariable("memberId") Integer id, NewMemberRequest request){
        Member member = memberRepository.findById(id).get();
        member.setName(request.name);
        member.setPhone(request.phone);
        member.setEmail(request.email);
        member.setAddress(request.address);
        memberRepository.save(member);
    }

    //transaction request
    @GetMapping("transaction")
    public List<Transaction> getTransaction(){
        return transactionRepository.findAll();
    }

    record NewTransactionRequest(Date date, Integer idMember, Integer idBook, Integer status){}

    @PostMapping("transaction")
    public void addTransaction(NewTransactionRequest request) {
        Transaction transaction = new Transaction();
        Book book = bookRepository.findById(request.idBook).orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if the transaction can proceed based on the status and the book's availability
        if ((request.status == 1 && book.getQuantity() > 0) || (request.status == 2 && book.getBooked() > 0)) {
            transaction.setTransactionDate(request.date);
            transaction.setIdMember(request.idMember);
            transaction.setIdBook(request.idBook);
            transaction.setStatus(request.status);
            transactionRepository.save(transaction);

            // Update book quantity and booked count based on the transaction status
            if (request.status == 1) {
                book.setQuantity(book.getQuantity() - 1);
                book.setBooked(book.getBooked() + 1);
            } else if (request.status == 2) {
                book.setQuantity(book.getQuantity() + 1);
                book.setBooked(book.getBooked() - 1);
            }

            // Save the updated book details
            bookRepository.save(book);
        } else {
            // Handle the case when the transaction cannot be processed
            throw new RuntimeException("Transaction cannot be processed due to insufficient book quantity or booked count");
        }
    }

    @DeleteMapping("transaction/{transactionId}")
    public void deleteTransaction(@PathVariable("transactionId") Integer id){
        transactionRepository.deleteById(id);
    }

    @PutMapping("transaction/{transactionId}")
    public void updateTransaction(@PathVariable("transactionId") Integer id, NewTransactionRequest request){
        Transaction transaction = transactionRepository.findById(id).get();
        Book book = bookRepository.findById(request.idBook).orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if the transaction can proceed based on the status and the book's availability
        if ((request.status == 1 && book.getQuantity() > 0) || (request.status == 2 && book.getBooked() > 0)) {
            transaction.setTransactionDate(request.date);
            transaction.setIdMember(request.idMember);
            transaction.setIdBook(request.idBook);
            transaction.setStatus(request.status);
            transactionRepository.save(transaction);

            // Update book quantity and booked count based on the transaction status
            if (request.status == 1) {
                book.setQuantity(book.getQuantity() - 1);
                book.setBooked(book.getBooked() + 1);
            } else if (request.status == 2) {
                book.setQuantity(book.getQuantity() + 1);
                book.setBooked(book.getBooked() - 1);
            }

            // Save the updated book details
            bookRepository.save(book);
        } else {
            // Handle the case when the transaction cannot be processed
            throw new RuntimeException("Transaction cannot be processed due to insufficient book quantity or booked count");
        }

    }





//
//    @GetMapping("/greet")
//    public GreetResponse greet() {
//        GreetResponse response = new GreetResponse("hello", List.of("java", "golang","javascript"),new
//                Person("Alex", 28, 30_000)
//        );
//        return response;
//    }
//
//    record Person(String name, int age, double savings) {
//    }
//    record GreetResponse(String greet, List<String> favProgrammingLanguages, Person person) { }
}
