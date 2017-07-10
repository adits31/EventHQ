package com.example.sampaniccia.learningthings.Activities;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.sampaniccia.learningthings.Adapters.AnnouncementAdapter;
import com.example.sampaniccia.learningthings.Adapters.DiscussionAdapter;
import com.example.sampaniccia.learningthings.Adapters.DrawerListAdapter;
import com.example.sampaniccia.learningthings.Adapters.MembersAdapter;
import com.example.sampaniccia.learningthings.Objects.Announcement;
import com.example.sampaniccia.learningthings.Objects.Comment;
import com.example.sampaniccia.learningthings.Objects.Member;
import com.example.sampaniccia.learningthings.Objects.MenuListItem;
import com.example.sampaniccia.learningthings.Objects.NewAnnouncementDialog;
import com.example.sampaniccia.learningthings.Objects.NewMemberDialog;
import com.example.sampaniccia.learningthings.R;
import com.example.sampaniccia.learningthings.Visual.AnnouncementDivider;
import com.example.sampaniccia.learningthings.Visual.AnnouncementSpecialDivider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewAnnouncementDialog.NoticeDialogListener, NewMemberDialog.NoticeDialogListener {

    //List items for this activity
    ArrayList<Announcement> announcementsList;
    ArrayList<Comment> commentList;
    ArrayList<Member> membersList;
    ArrayList<MenuListItem> menuListItems;

    //UI Components
    Toolbar toolbar;
    TabLayout tabs;
    Button annFooterButton, memFooterButton;
    ImageButton disFooterButton;
    EditText footerText;
    DialogFragment newAnnouncement, newMember;
    PopupMenu adminOptions;

    //Adapters
    AnnouncementAdapter announcementAdapter;
    DiscussionAdapter discussionAdapter;
    MembersAdapter membersAdapter;
    DrawerListAdapter drawerListAdapter;

    //Drawables
    Drawable dividerDrawable, specialDividerDrawable;

    //Layouts
    DrawerLayout drawerLayout;
    RecyclerView rView;
    ListView drawerListView;
    ViewFlipper flipper;

    //Other
    ActionBarDrawerToggle toggle;
    RecyclerView.ItemDecoration dividers, specialDividers;
    boolean isPinnedExist = false;
    int currentTab = 2;

    //Testing
    public static final String testName = "Nikki Potnick";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initNavBar();
        initTabLayout();
        initLists();
        initAdapters();
        initRecyclerView();
        initFooter();

        //flipper.setDisplayedChild(1);
        initOther();

    }

    private void initOther() {

    }

    public void adminMenu(ImageButton b, final Object o) {
        adminOptions = new PopupMenu(MainActivity.this, b);
        adminOptions.getMenuInflater().inflate(R.menu.menu_announcement_admin, adminOptions.getMenu());
        adminOptions.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Announcement a;
                switch (currentTab) {
                    case 1:

                        break;
                    case 2:
                        a = (Announcement) o;
                        int i = item.getOrder();
                        switch (i) {
                            case 1: //pin
                                if (!a.getPinned()) {
                                    if (!isPinnedExist) {
                                        isPinnedExist = true;
                                        a.setDrawNormalDivider(false);
                                    }
                                    announcementsList.remove(a);
                                    announcementsList.add(0, a);
                                    announcementAdapter.notifyDataSetChanged();

                                } else {
                                    //nothing? dialog?
                                }
                                break;
                            case 2: //unpin
                                if (a.getPinned()) {
                                    if (!a.getDrawNormalDivider()) {
                                        int index = announcementsList.indexOf(a);
                                        if (index != 0) {
                                            announcementsList.get(index - 1).setDrawNormalDivider(false);
                                        } else {
                                            isPinnedExist = false;
                                        }
                                        a.setDrawNormalDivider(true);
                                        announcementsList.remove(a);
                                        announcementsList.add(a);
                                        announcementAdapter.notifyDataSetChanged();
                                    } else {
                                        announcementsList.remove(a);
                                        announcementsList.add(a);
                                        announcementAdapter.notifyDataSetChanged();
                                    }
                                } else {

                                }
                                break;
    /**/
                            case 3: //delete
                                if (a.getPinned()) { //if a is pinned...
                                    if (!a.getDrawNormalDivider()) {
                                        int index = announcementsList.indexOf(a);
                                        if (index != 0) {
                                            announcementsList.get(index - 1).setDrawNormalDivider(false);
                                        } else {
                                            isPinnedExist = false;
                                        }
                                    }
                                }
                                announcementsList.remove(a);
                                announcementAdapter.notifyDataSetChanged();
                                //announcementAdapter.notifyItemRangeChanged(0,announcementsList.size());
                                break;
                        }
                        //doAdminMenuFunction(i);
                        return true;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                }
                //doAdminMenuFunction(i);
                return true;
            }
        });
        adminOptions.show();
    }


    private void initFooter() {
        annFooterButton = (Button) findViewById(R.id.event_ann_footer_button);
        annFooterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNewAnnouncementDialog(v);
            }
        });

        footerText = (EditText) findViewById(R.id.event_footer_editText);

        disFooterButton = (ImageButton) findViewById(R.id.event_dis_footerButton);
        disFooterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String message = footerText.getText().toString();
                if(!message.equals("")) {
                    addComment(new Comment(testName, message));
                    footerText.setText("");
                }
            }
        });

        memFooterButton = (Button) findViewById(R.id.event_mem_footer_button);
        memFooterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toNewMemberDialog(v);
            }
        });

        flipper = (ViewFlipper) findViewById(R.id.event_flipper);
        flipper.setAutoStart(false);

    }

    private void addComment(Comment c) {
        commentList.add(c);
        discussionAdapter.notifyDataSetChanged();
    }


    private void toNewAnnouncementDialog(View view) {
        newAnnouncement = new NewAnnouncementDialog();
        newAnnouncement.show(getSupportFragmentManager(), "newAnnouncement");
    }

    private void toNewMemberDialog(View v){
        newMember = new NewMemberDialog().setActivity(this);
        newMember.show(getSupportFragmentManager(), "newMember");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //Override this if trying to add to toolbar (Add picture, etc)
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.hamburger_menu:
                System.out.println("What is going on");
            default:
                return super.onOptionsItemSelected(item);
        }*/
        return true;
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("bar");
            }
        });
    }

    private void initNavBar() {
        menuListItems = new ArrayList<MenuListItem>();
        menuListItems.add(new MenuListItem("Home", "Subtitle", R.drawable.ic_home_black_24dp));


        drawerLayout = (DrawerLayout) findViewById(R.id.event_drawerLayout);
        drawerListAdapter = new DrawerListAdapter(this, menuListItems);
        drawerListView = (ListView) findViewById(R.id.navList);
        drawerListView.setAdapter(drawerListAdapter);

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void selectItemFromDrawer(int pos) {
        //create Intents and launch Activities, etc
    }

    private void initTabLayout() {
        tabs = (TabLayout) findViewById(R.id.tab_layout);
        tabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabs.addTab(tabs.newTab().setText("Description").setTag("1"));
        tabs.addTab(tabs.newTab().setText("Announcements").setTag("2"));
        tabs.addTab(tabs.newTab().setText("Discussion Board").setTag("3"));
        tabs.addTab(tabs.newTab().setText("Costs ($X)").setTag("4"));
        tabs.addTab(tabs.newTab().setText("Members").setTag("5"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                  @Override
                  public void onTabSelected(TabLayout.Tab tab) {
                      int tag = Integer.valueOf(tab.getTag().toString());
                      switch (tag) {
                          case 1: //Description

                              break;
                          case 2: //Announcements
                              rView.setAdapter(announcementAdapter);
                              flipper.setDisplayedChild(0); //change eventually
                              break;
                          case 3: //Discussion Board
                              rView.setAdapter(discussionAdapter);
                              flipper.setDisplayedChild(1); //change eventually
                              break;
                          case 4: //Costs

                              break;
                          case 5: //Members
                              rView.setAdapter(membersAdapter);
                              flipper.setDisplayedChild(2); //change eventually
                              break;
                      }
                  }

                  @Override
                  public void onTabUnselected(TabLayout.Tab tab) {

                  }

                  @Override
                  public void onTabReselected(TabLayout.Tab tab) {

                  }
              }
        );
    }

    private void initLists() {
        announcementsList = new ArrayList<Announcement>();
        commentList = new ArrayList<Comment>();
        membersList = new ArrayList<Member>();
        //announcementsList.add(new Announcement("Sam", "Hey, remember to park on the right side of the parking lot.", false));
        //announcementsList.add(new Announcement("Felix", "Bringing three bags of Doritos, flavor requests?", false));
        //announcementsList.add(new Announcement("Rich", "Stack?", false));
        //announcementsList.add(new Announcement("Aditya", "Gonna be a bit late, but who's surprised?", false));
        //announcementsList.add(new Announcement("Michael", "Can I bring my friend Vivek?", true));
        //announcementsList.add(new Announcement("Vivek", "V is for Vivek, Vivek is for V", true));
        //announcementsList.add(new Announcement("Ryan", "Cream", true));

     /**/
        //isFirstPinnedAdded = false; //FOR TESTING: put in Event data object!
    }

    private void addAnnouncement(Announcement a) {
        if (!a.getPinned()) { //if a is not pinned, add to end of list
            announcementsList.add(a);
            //announcementAdapter.notifyItemRangeChanged(0, announcementsList.size());
            announcementAdapter.notifyDataSetChanged();

        } else {    //if a is pinned...
            if (!isPinnedExist) { //...and there is no pinned announcement
                a.setDrawNormalDivider(false);
                isPinnedExist = true; //pinned do exist, set true
            } else { //...and there are pinned announcements

            }
            announcementsList.add(0, a);
            //announcementAdapter.notifyItemRangeChanged(0, announcementsList.size());
            announcementAdapter.notifyDataSetChanged();
        }
    }

    private void addMember(Member m) {
        membersList.add(m);
        membersAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        rView = (RecyclerView) findViewById(R.id.event_recyclerview);

        rView.setAdapter(announcementAdapter);
        rView.setLayoutManager(new LinearLayoutManager(this));

        dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider);
        specialDividerDrawable = ContextCompat.getDrawable(this, R.drawable.special_divider);

        dividers = new AnnouncementDivider(dividerDrawable);
        specialDividers = new AnnouncementSpecialDivider(specialDividerDrawable);
        rView.addItemDecoration(dividers);
        rView.addItemDecoration(specialDividers);
    }

    private void initAdapters() {
        announcementAdapter = new AnnouncementAdapter(this, announcementsList);
        discussionAdapter = new DiscussionAdapter(this, commentList);
        membersAdapter = new MembersAdapter(this, membersList);
    }

    @Override
    public void onDialogPositiveClick(Announcement announcement) {
        addAnnouncement(announcement);
    }

    @Override
    public void onDialogPositiveClick(Member member) {
        addMember(member);
    }
}
