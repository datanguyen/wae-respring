import React from 'react'
import {connect} from 'react-redux'
import { CartView } from './cart.view'

@connect(state => ({cart: state.cart}))
export class CartComponent extends React.Component {

    render() {
        return <CartView cart={this.props.cart} />
    }
}
