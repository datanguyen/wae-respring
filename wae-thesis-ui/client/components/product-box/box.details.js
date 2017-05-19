import React from 'react'
import { MuiThemeProvider, RaisedButton, Dialog, Snackbar } from 'material-ui'
import { ProductDialog } from './box.dialog'

export class BoxDetails extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      viewDetailOpen: false,
      addToCartOpen: false
    }
  }

  handleDetailOpen() {
    this.setState({viewDetailOpen: true})
  }

  handleDetailClose() {
    this.setState({viewDetailOpen: false})
  }

  handleCartOpen() {
    this.setState({ addToCartOpen: true })
  }

  handleCartRequestClose() {
    this.setState({ addToCartOpen: false })
  }

  render() {
    const actions = [
      <MuiThemeProvider>
        <RaisedButton
          label="Ok"
          secondary={true}
          keyboardFocused={true}
          onTouchTap={(e) => this.handleDetailClose(e)}
        />
      </MuiThemeProvider>
    ]
    let { productName, productUrl, productPrice} = this.props

    return (
      <div>
        <MuiThemeProvider>
          <RaisedButton label="ADD TO CART"
                        labelPosition="before"
                        backgroundColor="#252f3e"
                        labelColor="white"
                        style={{margin: "10px 0 10px 0"}}
                        onTouchTap={(e) => this.handleCartOpen(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <RaisedButton label="VIEW DETAILS"
                        labelPosition="before"
                        backgroundColor="#252f3e"
                        labelColor="white"
                        style={{margin: "10px 0 10px 10px"}}
                        onTouchTap={(e) => this.handleDetailOpen(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <Snackbar
            open={this.state.addToCartOpen}
            message="Product added to your cart"
            autoHideDuration={6000}
            bodyStyle={{backgroundColor: "black"}}
            onRequestClose={(e) => this.handleCartRequestClose(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <Dialog
            actions={actions}
            modal={true}
            open={this.state.viewDetailOpen}
            onRequestClose={(e) => this.handleDetailClose(e)}
            contentStyle={{width: "600px"}}
          >
            <ProductDialog
              productName={productName}
              productPrice={productPrice}
              productUrl={productUrl}
            />
          </Dialog>
        </MuiThemeProvider>
      </div>
    )
  }
}

BoxDetails.propTypes = {
  productName: React.PropTypes.string.isRequired,
  productPrice: React.PropTypes.number.isRequired,
  productUrl: React.PropTypes.string.isRequired
}