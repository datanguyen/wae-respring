import React from 'react'
import { view } from 'core'
import {NavbarView} from './navbar.view'
import {connect} from 'react-redux'

@connect(state => ({authenticate: state.authenticate}))
export class Navbar extends React.Component {

    render() {
        return <NavbarView authenticate={this.props.authenticate} />
    }
}
