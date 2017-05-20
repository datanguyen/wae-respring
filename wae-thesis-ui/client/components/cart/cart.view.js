import React from 'react'
import {MuiThemeProvider, RaisedButton, Drawer, MenuItem} from 'material-ui'

export class CartView extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      cartOpen: false
    }
  }

  handlerCartToggle() {
    this.setState({ cartOpen: !this.state.cartOpen })
  }

  render() {
    return (
      <div>
        <MuiThemeProvider>
          <RaisedButton label="Cart"
                        labelPosition="before"
                        backgroundColor="#252f3e"
                        labelColor="white"
                        onTouchTap={() => this.handlerCartToggle()}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <Drawer
            docked={false}
            width={600}
            openSecondary={true}
            open={this.state.cartOpen}
            onRequestChange={() => this.handlerCartToggle()}
          >
            <MuiThemeProvider>
              <MenuItem >Menu Item</MenuItem>
            </MuiThemeProvider>
          </Drawer>
        </MuiThemeProvider>
      </div>
    )
  }
}

CartView.propTypes = {
  cart: React.PropTypes.array.isRequired
}
