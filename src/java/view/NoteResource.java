/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import nus.edu.iss.ejava.bean.NoteBean;
import nus.edu.iss.ejava.bean.UserBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import nus.edu.iss.ejava.model.Note;

/**
 *
 * @author Snow
 */
@Named
@RequestScoped
public class NoteResource {
    @EJB private NoteBean noteBean;
    @EJB private UserBean userBean;

    private String category;
    private String title;
    private String content;
    private Timestamp createTime;
    private List<String> categoryList;
    
    private List<JsonObject> noteList;
    private List<JsonObject> allNotes;

    @PostConstruct
    private void init() {
        this.categoryList = new ArrayList<String>();
        this.categoryList.add("Social"); 
        this.categoryList.add("For Sale");
        this.categoryList.add("Jobs");
        this.categoryList.add("Tuition");
    }
    
    public void createNote() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            Note note = new Note();
            note.setContent(content);
            note.setCreateDate(new Date());
            note.setTitle(title);
            note.setUser(userBean.findUserById(ec.getRemoteUser()));
//            note.setUser(userBean.findUserById("123"));
            note.setCategory(category);
            noteBean.createNote(note);
//            sendMessageOverSocket(note.toJSON().toString());
            ec.redirect(ec.getRequestContextPath() + "/faces/manage/postednote.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(NoteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<JsonObject> getAllNotes() {
        List<Note> lstNote = noteBean.getAllNotes();
        List<JsonObject> list = new ArrayList<>();
        for (Note note: lstNote) {
            list.add(note.toJSON());
        }
        return list;
    }
    
    public List<JsonObject> getNoteList() {
        List<Note> list = noteBean.getAllNotesByUserId(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
//        List<Note> list = noteBean.getAllNotesByUserId("123");
        List<JsonObject> noteList = new ArrayList<JsonObject>();
        for (Note note: list) {
            noteList.add(note.toJSON());
        }
        return noteList;
    }

    public String getContent() {
        return content;
    }

    public List<String> getCategoryList() {
        return (categoryList);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategoryList(List<String> categorylist) {
        this.categoryList = categorylist;
    }
    
    public String getName(){
        return "fuck you";
    }


    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
         
}
