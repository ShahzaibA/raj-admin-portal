import React from 'react';
import { Link } from 'react-router-dom';
import { Drawer, List, ListItem, ListItemText, IconButton, Divider} from "@material-ui/core";
import HomeLogo from '@material-ui/icons/Home';
import VerifiedUserIcon from '@material-ui/icons/VerifiedUser';

import MenuIcon from '@material-ui/icons/Menu';

function SideBar(props){
    const [open, setOpen] = React.useState(false);
    const [links, setLinks] = React.useState([]);

    React.useEffect(() => {
        fetch('http://159.89.188.124:8080/links/' + localStorage.getItem('role'), {
            method: "GET"
        })
        .then(res => res.json())
        .then(result => setLinks(result))
      });

      
    function returnOut(){
        if(localStorage.getItem('role') === 'UNASSIGNED'){
            return <div/>
        }
        else if(localStorage.getItem('role') === 'SUPER_ADMIN'){
            return <List>
            <Link to="/admin" style={{ textDecoration: 'none' }}>
                <ListItem button>
                    <VerifiedUserIcon/>
                    <ListItemText primary='Modify Users' />
                </ListItem>
            </Link>
            <Link to="/links" style={{ textDecoration: 'none' }}>
                <ListItem button>
                <VerifiedUserIcon/>
                    <ListItemText primary='Modify Links' />
                </ListItem>
            </Link>
            <Link to="/roles" style={{ textDecoration: 'none' }}>
                <ListItem button>
                <VerifiedUserIcon/>
                    <ListItemText primary='Modify Roles' />
                </ListItem>
            </Link>
        </List>
        }
        else{
            return <List>
            {links.map(item => 
            <Link to='/redirect' style={{ textDecoration: 'none' }}>
            <ListItem button onClick={() => localStorage.setItem('link',item.link)}>
                <ListItemText primary={item.link} />
            </ListItem>
            </Link>
            )}
        </List>
        }
    }
    return (
        <React.Fragment>
            <IconButton edge="start" onClick={() => setOpen(true)} aria-label="menu">
                <MenuIcon style={{color: 'white'}}/>
            </IconButton>
            <Drawer anchor="left" open={open} onClose={() => setOpen(false)}>
            <div onClick={()=> setOpen(false)}>
                <List>
                    <Link to="/" style={{ textDecoration: 'none' }}>
                        <ListItem button>
                            <HomeLogo/>
                            <ListItemText primary='Home' />
                        </ListItem>
                    </Link>
                </List>
                <Divider />
                {returnOut()}
            </div>
            </Drawer>
        </React.Fragment>
    );
}

export default SideBar;