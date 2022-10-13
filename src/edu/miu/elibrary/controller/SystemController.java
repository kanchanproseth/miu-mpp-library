package edu.miu.elibrary.controller;

import edu.miu.elibrary.business.Address;
import edu.miu.elibrary.business.Book;
import edu.miu.elibrary.business.LibraryMember;
import edu.miu.elibrary.dataaccess.DataAccessFacade;
import edu.miu.elibrary.dataaccess.User;
import edu.miu.elibrary.security.Principal;
import edu.miu.elibrary.security.SecurityContext;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Kuylim TITH
 * Date: 10/12/2022
 */
public class SystemController {

    private DataAccessFacade dataAccess;
    private List<LibraryMember> libraryMembers;

    public SystemController() {
        dataAccess = new DataAccessFacade();
    }

    public List<Book> getAllBooks() {
        System.out.println(new ArrayList<>(dataAccess.readBooksMap().values()));
        return null;
    }

    public LibraryMember addMember(LibraryMember member) {
        return null;
    }

    public boolean login(String username, String password) {
        List<User> users = dataAccess.readUserMap().values().stream().toList();
        for (User user : users) {
            if (username.equals(user.getId()) && password.equals(user.getPassword())) {
                SecurityContext.principal = new Principal(user.getAuthorization(), user.getId());
                return true;
            }
        }
        return false;
    }

    public Address createAddress(String street, String city, String state, String zip) {
        return new Address(street, city, state, zip);
    }

    // ========== Library Member

    public void loadAllLibraryMember() {
        libraryMembers = dataAccess.readMemberMap().values().stream().toList();
    }

    public DefaultListModel<LibraryMember> getLibraryMemberListModel() {
        DefaultListModel<LibraryMember> listModel = new DefaultListModel<>();
        libraryMembers = dataAccess.readMemberMap().values().stream().toList();
        for (LibraryMember libraryMember : libraryMembers) {
            listModel.addElement(libraryMember);
        }
        return listModel;
    }

    public LibraryMember createLibraryMember(String firstName, String lastName, String phoneNumber) {
        String id = String.valueOf(libraryMembers.size() + 1 + 1000);
        return new LibraryMember(id, firstName, lastName, phoneNumber);
    }

    public void addLibraryMember(LibraryMember member) {
        dataAccess.saveNewMember(member);
        loadAllLibraryMember();
    }

    public void updateLibraryMember() {
        // todo
    }

    // ========== Library Member
    public Book createBook() {
        return null;
    }
}