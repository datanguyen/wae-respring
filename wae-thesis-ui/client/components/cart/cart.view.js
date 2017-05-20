import React from 'react'
import {MuiThemeProvider, RaisedButton, Drawer, List, Subheader, Divider} from 'material-ui'
import { CartElement } from './cart.element'

export class CartView extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      cartOpen: false
    }
  }

  handlerCartToggle() {
    this.setState({cartOpen: !this.state.cartOpen})
  }

  render() {
    return (
      <div>
        <MuiThemeProvider>
          <RaisedButton label={`Cart (${this.props.cart.length})`}
                        labelPosition="before"
                        backgroundColor="#252f3e"
                        labelColor="yellow"
                        labelStyle={{fontWeight: "bolder"}}
                        onTouchTap={() => this.handlerCartToggle()}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <Drawer
            docked={false}
            width={450}
            openSecondary={true}
            open={this.state.cartOpen}
            onRequestChange={() => this.handlerCartToggle()}
          >
            <List>
              <Subheader style={{color: "black", fontSize: "25px"}}>Your Shopping's Cart</Subheader>
              {
                this.props.cart.map(product => {
                  let { productName, productPrice, productUrl } = product
                  return (
                    <CartElement
                      id={product.id}
                      product={{ productName, productPrice, productUrl }}
                      deleteProductCart={this.props.deleteProductCart}
                    />
                  )
                })
              }
              <Divider />
              { !this.props.authenticate.authenticated
              && <p style={{margin: "20px 0 0 40%", color: "red", fontWeight: "bolder"}}>
                *You need to sign in before checkout</p>
              }
              <RaisedButton label="Proceed to checkout"
                            labelPosition="before"
                            backgroundColor="#252f3e"
                            labelColor="white"
                            style={{margin: "10px 0 0 50%"}}
                            onTouchTap={() => this.handlerCartToggle()}
                            disabled={!this.props.authenticate.authenticated}
              />
            </List>
          </Drawer>
        </MuiThemeProvider>
      </div>
    )
  }
}

CartView.propTypes = {
  cart: React.PropTypes.array.isRequired,
  deleteProductCart: React.PropTypes.func.isRequired,
  authenticate: React.PropTypes.object.isRequired
}
