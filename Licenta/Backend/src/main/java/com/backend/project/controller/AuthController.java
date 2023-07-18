package com.backend.project.controller;

import com.backend.project.model.*;
import com.backend.project.repository.*;
import com.backend.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    private ResponseEntity<?> signupClient(@RequestBody AuthenticationRequest auth){
        String username = auth.getUsername();
        String password = auth.getPassword();
        String name = auth.getName();
        String email = auth.getEmail();
        String phoneNumber = auth.getPhoneNumber();
        int roleId = auth.getRoleId();
        User user = new User();
        if(userRepository.findByUsername(username)==null){
            user.setId(service.getSequence(User.sequenceName));
            user.setUsername(username);
            user.setPassword(password);
            user.setRoleId(roleId);
            user.setProductId(user.getId());
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
        }else {
            return ResponseEntity.ok(new AuthenticationResponse("Username already exists"));
        }
        try {
            userRepository.save(user);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error during Sign in "+username));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Successful Sign In for client "+ username));
    }

    @CrossOrigin(origins = "http://localhost:63342/frontend/JS/login.html")
    @PostMapping("/login")
    private ResponseEntity<?> loginClient(@RequestBody AuthenticationRequest auth){
        String username = auth.getUsername();
        String password = auth.getPassword();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error during Log in"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Successful Log in for client "+ username));

    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/users/{_id}")
    public Object getUser(@PathVariable int _id){
        return userRepository.findById(_id);
    }
    @DeleteMapping("/deleteUser/{productId}")
    public ResponseEntity<?> delete( @PathVariable int productId){
        User user = new User();
        try {
            user.setProductId(userRepository.findByProductId(productId).getProductId());
            userRepository.deleteById(productId);
            productRepository.deleteByProductId(productId);
        }catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("User failed to be deleted"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("User deleted successfully"));
    }

    @GetMapping("/listAllProducts")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/addProduct/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable int productId, @RequestBody ProductResponse productRequest) {

        String productName = productRequest.getProductName();
        String productDetails = productRequest.getProductDetails();
        String category = productRequest.getCategory();
        String availability = productRequest.getAvailability();
        String latitude = productRequest.getLatitude();
        String longitude = productRequest.getLongitude();
        String location = productRequest.getLocation();
        int quantity = productRequest.getQuantity();
        Product product = new Product();

        product.setId(service.getSequence(Product.sequenceName));
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDetails(productDetails);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setAvailability(availability);
        product.setLatitude(latitude);
        product.setLongitude(longitude);
        product.setLocation(location);
        try{
            productRepository.save(product);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Product failed to be added"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Product successfully added"));
    }

    @GetMapping("/getUserInfo/{username}")
    public Object getUserInfo(@PathVariable String username){
       InfoResponse response = new InfoResponse();
       response.setId(userRepository.findByUsername(username).getId());
       response.setRoleId(userRepository.findByUsername(username).getRoleId());
       response.setName(userRepository.findByUsername(username).getName());
       response.setEmail(userRepository.findByUsername(username).getEmail());
       return response;
    }

    @GetMapping("/listProducts/{productId}")
    public List<Product> listProducts(@PathVariable int productId){
        return productRepository.findByProductId(productId);
    }
    @DeleteMapping("/deleteProduct/{_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int _id){
        Product product = new Product();
        try{
            product.setProductId(productRepository.findById(_id).getId());
            productRepository.deleteById(_id);
        }catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Product failed to be deleted"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Product successfully deleted"));
    }

    @PostMapping("/updateProduct/{_id}")
    public ResponseEntity<?>updateProduct(@PathVariable int _id, @RequestBody ProductResponse productRequest){
        Product foundedProduct = productRepository.findById(_id);
        try{
            foundedProduct.setProductName(productRequest.getProductName());
            foundedProduct.setCategory(productRequest.getCategory());
            foundedProduct.setAvailability(productRequest.getAvailability());
            foundedProduct.setQuantity(productRequest.getQuantity());
            foundedProduct.setProductDetails(productRequest.getProductDetails());
            productRepository.save(foundedProduct);
        }catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Update Failed"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Product successfully updated"));
    }

    @PostMapping("/addDonation")
    public ResponseEntity<?>addDonation(@RequestBody DonationRequest donationRequest){
        int amount = donationRequest.getAmount();
        String name = donationRequest.getName();
        String email = donationRequest.getEmail();
        String mode = donationRequest.getMode();
        Donation donation = new Donation();
        donation.setAmount(amount);
        donation.setName(name);
        donation.setEmail(email);
        donation.setMode(mode);
        try{
            donationRepository.save(donation);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Donation failed to be registered"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Donation registered"));
    }

    @GetMapping("/donations")
    public List<Donation> getDonations(){
        return donationRepository.findAll();
    }

    @PostMapping("/addMessage")
    public ResponseEntity<?> addMessage(@RequestBody ContactMessageRequest messageRequest){
        ContactMessage contactMessage = new ContactMessage();
        String name = messageRequest.getName();
        String email = messageRequest.getEmail();
        String message = messageRequest.getMessage();
        contactMessage.setName(name);
        contactMessage.setEmail(email);
        contactMessage.setMessage(message);
        try{
            messageRepository.save(contactMessage);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Message failed to be send"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("We will contact you soon"));
    }

    @GetMapping("/messages")
    public List<ContactMessage> getMessages(){
        return messageRepository.findAll();
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody EventRequest eventRequest){
        Event event = new Event();
        String name = eventRequest.getName();
        String date = eventRequest.getDate();
        String description = eventRequest.getDescription();
        String location = eventRequest.getLocation();
        String address = eventRequest.getAddress();
        String program = eventRequest.getProgram();
        int spotsAvailable = eventRequest.getSpotsAvailable();
        String eventCode = service.generateEventCode();

        event.setId(service.getSequence(Event.sequenceName));
        event.setEventCode(eventCode);
        event.setName(name);
        event.setDate(date);
        event.setDescription(description);
        event.setLocation(location);
        event.setAddress(address);
        event.setProgram(program);
        event.setSpotsAvailable(spotsAvailable);

        try{
            eventRepository.save(event);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Event can't be added"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Event added"));
    }

    @GetMapping("/events")
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    @DeleteMapping("/deleteEvent/{eventCode}")
    public ResponseEntity<?> deleteEvent(@PathVariable String eventCode){
        Event event = new Event();
        try{
            event.setEventCode(eventRepository.findEventByEventCode(eventCode).getEventCode());
            eventRepository.deleteByEventCode(event.getEventCode());
        }catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Event failed to be deleted"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Event successfully deleted"));
    }

    @GetMapping("/getEvent/{name}")
    public Object getEvent(@PathVariable String name){
        EventRequest eventRequest = new EventRequest();
        Event foundedEvent = eventRepository.findEventByName(name);
        eventRequest.setName(foundedEvent.getName());
        eventRequest.setEventCode(foundedEvent.getEventCode());
        eventRequest.setAddress(foundedEvent.getAddress());
        eventRequest.setDate(foundedEvent.getDate());
        eventRequest.setLocation(foundedEvent.getLocation());
        eventRequest.setProgram(foundedEvent.getProgram());
        eventRequest.setSpotsAvailable(foundedEvent.getSpotsAvailable());
        eventRequest.setDescription(foundedEvent.getDescription());
        return eventRequest;
    }

    @PutMapping("/bookVolunteerSpots/{eventCode}")
    public ResponseEntity<?>updateEvent(@PathVariable String eventCode, @RequestBody EventRequest eventRequest){
        Event foundedEvent = eventRepository.findEventByEventCode(eventCode);
        int difference = foundedEvent.getSpotsAvailable()-eventRequest.getSpotsAvailable();
        try{
            if(difference>=0){
                foundedEvent.setSpotsAvailable(difference);
                eventRepository.save(foundedEvent);
            }else{
                return ResponseEntity.ok(new AuthenticationResponse("There are not enough spots"));
            }
        }catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("There are not enough spots"));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Spots booked"));
    }
}
