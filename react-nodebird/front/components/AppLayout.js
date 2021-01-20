import React from "react";
import PropTypes from 'prop-types';
import Link from "next/link";

const AppLayOut = ({ children }) => {
    return (
        <div>
            <Link href="/"><a>alxndr</a></Link>
            <Link href="/profile"><a>profile</a></Link>
            <Link href="/signup"><a>signUp</a></Link>
            {children}
        </div>
    );
}

AppLayOut.propTypes = {
    children: PropTypes.node.isRequired,
}



export default AppLayOut;