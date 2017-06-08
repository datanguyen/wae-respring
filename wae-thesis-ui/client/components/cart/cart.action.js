import { ADD_PRODUCT_CART, DELETE_PRODUCT_CART } from './cart.action.types'

export const addProductCart = (product) => {
  let currentCart = localStorage.waeCart || []

  if (currentCart.length === 0) {
    return {
      type: ADD_PRODUCT_CART,
      product
    }
  }

  let index = JSON.parse(currentCart).findIndex(cartProduct => {
    let { productName, productPrice, productUrl } = cartProduct

    return productName === product.productName
      && productPrice === product.productPrice
      && productUrl === product.productUrl
  })

  return index >= 0
    ? { type: 'ADD_FAILED', product }
    : { type: ADD_PRODUCT_CART, product}

}

export const deleteProductCart = (productId) => {
  return {
    type: DELETE_PRODUCT_CART,
    productId
  }
}