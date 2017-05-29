package org.dongchao.security.core.userdetails.jdbc;

import org.dongchao.security.core.userdetails.SsUser;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义UserDetailService的实现，方便日后功能扩展;
 * 主要方法为loadUserByUsername ;
 * Created by zhaodongchao on 2017/5/29.
 */
public class MyJdbcDaoImpl extends JdbcDaoSupport implements UserDetailsService {

    public static final String DEF_USERS_BY_USERNAME_QUERY = "select user_id, username, password  from dc_user where username = ?";
    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select dr.role_code from dc_role dr where dr.role_id in (select dur.role_id  from dc_user_role dur where dur.user_id in (select du.user_id from dc_user du where du.username=?))";

    private String usersByUsernameQuery;
    private String authoritiesByUsernameQuery;
    private boolean usernameBasedPrimaryKey = true;
    private boolean enableAuthorities = true;
    private String rolePrefix = "";

    protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public MyJdbcDaoImpl() {
        usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
        authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SsUser> users = loadUsersByUsername(username);

        if (users.size() == 0) {
            logger.debug("Query returned no results for user '" + username + "'");

            throw new UsernameNotFoundException(messages.getMessage(
                    "MyJdbcDaoImpl.notFound", new Object[] { username },"Username {0} not found"));
        }
        SsUser user = users.get(0); // contains no GrantedAuthority[]

        Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

        if (enableAuthorities) {
            dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
        }

        List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);

        addCustomAuthorities(user.getUsername(), dbAuths);

        if (dbAuths.size() == 0) {
            logger.debug("User '" + username
                    + "' has no authorities and will be treated as 'not found'");

            throw new UsernameNotFoundException(messages.getMessage(
                    "JdbcDaoImpl.noAuthority", new Object[] { username },
                    "User {0} has no GrantedAuthority"));
        }
        return createUserDetails(username, user, dbAuths);
    }

    /**
     * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of UserDetails
     * objects. There should normally only be one matching user.
     */
    protected List<SsUser> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(usersByUsernameQuery, new String[]{username},
                new RowMapper<SsUser>() {
                    public SsUser mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        String userId = rs.getString(1);
                        String username = rs.getString(2);
                        String password = rs.getString(3);
                        boolean enabled = true;
                        return new SsUser(userId,username, password, enabled, true, true, true,
                                AuthorityUtils.NO_AUTHORITIES);
                    }

                });
    }

    /**
     * Loads authorities by executing the SQL from <tt>authoritiesByUsernameQuery</tt>.
     *
     * @return a list of GrantedAuthority objects for the user
     */
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        return getJdbcTemplate().query(authoritiesByUsernameQuery,
                new String[] { username }, new RowMapper<GrantedAuthority>() {
                    public GrantedAuthority mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        String roleName = rolePrefix + rs.getString(1);

                        return new SimpleGrantedAuthority(roleName);
                    }
                });
    }
    /**
     * Allows subclasses to add their own granted authorities to the list to be returned
     * in the <tt>UserDetails</tt>.
     *
     * @param username the username, for use by finder methods
     * @param authorities the current granted authorities, as populated from the
     * <code>authoritiesByUsername</code> mapping
     */
    protected void addCustomAuthorities(String username, List<GrantedAuthority> authorities) {
    }

    /**
     * Can be overridden to customize the creation of the final UserDetailsObject which is
     * returned by the <tt>loadUserByUsername</tt> method.
     *
     * @param username the name originally passed to loadUserByUsername
     * @param userFromUserQuery the object returned from the execution of the
     * @param combinedAuthorities the combined array of authorities from all the authority
     * loading queries.
     * @return the final UserDetails which should be used in the system.
     */
    protected SsUser createUserDetails(String username,SsUser userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
        String userId = userFromUserQuery.getUserId();
        String returnUsername = userFromUserQuery.getUsername();

        if (!usernameBasedPrimaryKey) {
            returnUsername = username;
        }

        return new SsUser(userId,returnUsername, userFromUserQuery.getPassword(),
                userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities);
    }
}
