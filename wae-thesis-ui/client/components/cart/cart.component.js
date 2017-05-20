import React from 'react'
import {connect} from 'react-redux'
import { CartView } from './cart.view'
import { deleteProductCart } from './cart.action'

@connect(state => ({authenticate: state.authenticate, cart: state.cart}), { deleteProductCart })
export class CartComponent extends React.Component {

    render() {
        return <CartView
          cart={this.props.cart}
          deleteProductCart={this.props.deleteProductCart}
         authenticate={this.props.authenticate}
        />
    }
}

CartComponent.propTypes = {
  deleteProductCart: React.PropTypes.func.isRequired,
  authenticate: React.PropTypes.object.isRequired
}
